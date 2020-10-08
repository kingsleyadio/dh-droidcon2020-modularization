package com.deliveryhero.workshop.dc2020.common

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.rules.RuleChain
import org.junit.rules.TestRule

fun testRules(): TestRule = RuleChain.outerRule(TrampolineRule()).around(InstantTaskExecutorRule())
