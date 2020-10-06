package com.deliveryhero.translation.codegen

import com.deliveryhero.translation.annotation.GenerateTranslationKeys
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.File
import java.util.Locale
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.lang.model.util.ElementFilter
import javax.tools.Diagnostic
import javax.xml.parsers.DocumentBuilderFactory

@SupportedAnnotationTypes("com.deliveryhero.translation.annotation.GenerateTranslationKeys")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
class TranslationProcessor : AbstractProcessor() {

    override fun process(annotations: MutableSet<out TypeElement>, roundEnv: RoundEnvironment): Boolean {
        return roundEnv.getElementsAnnotatedWith(GenerateTranslationKeys::class.java)
            .let { ElementFilter.typesIn(it) }
            .all { processElement(it) }
    }

    private fun processElement(element: TypeElement): Boolean {
        val params = element.getAnnotation(GenerateTranslationKeys::class.java)
        processingEnv.messager.printMessage(
            Diagnostic.Kind.NOTE,
            "packageName = ${params.packageName}, className = ${params.className}, path = ${params.stringsPath}"
        )
        val content = FileSpec.builder(params.packageName, params.className)
            .addType(
                TypeSpec.objectBuilder(params.className)
                    .generateClassDoc(params)
                    .generateProperties(params)
                    .build()
            ).build()

        val result = runCatching {
            content.writeTo(processingEnv.filer)
        }.onFailure {
            processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, "Could not write generated class : $it")
        }

        return result.isSuccess
    }

    private fun TypeSpec.Builder.generateClassDoc(params: GenerateTranslationKeys) = apply {
        addKdoc("Generated class: ${params.packageName}.${params.className}\nSource: ${params.stringsPath}")
    }

    private fun TypeSpec.Builder.generateProperties(params: GenerateTranslationKeys) = apply {
        runCatching {
            readStringsXml(params.stringsPath).forEach { (k, _) ->
                addProperty(
                    PropertySpec.builder(k.toUpperCase(Locale.ENGLISH), String::class, KModifier.CONST)
                        .initializer("\"$k\"")
                        .build()
                )
            }
        }.onFailure {
            processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, "Error happened: $it")
        }
    }

    private fun readStringsXml(resourcePath: String): Map<String, String> {
        val file = File(resourcePath).absoluteFile
        val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file)
        val resourcesElement = doc.documentElement

        return if (resourcesElement.hasChildNodes()) getChildNodes(resourcesElement.childNodes) else emptyMap()
    }

    private fun getChildNodes(nodeList: NodeList) = buildMap<String, String> {
        for (count in 0 until nodeList.length) {
            val node = nodeList.item(count)
            if (node.nodeType == Node.ELEMENT_NODE) {
                put(node.attributes.getNamedItem("name").nodeValue, node.textContent)
            }
        }
    }
}
