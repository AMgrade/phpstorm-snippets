package com.amgrade.phpstorm.snippets

import com.intellij.codeInsight.template.TemplateActionContext
import com.intellij.codeInsight.template.TemplateContextType

internal class PhpContext : TemplateContextType("PHP", "PHP") {
    override fun isInContext(templateActionContext: TemplateActionContext): Boolean {
        return templateActionContext.file.name.endsWith(".php")
    }
}