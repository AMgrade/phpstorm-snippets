package com.amgrade.phpstorm.snippets

import com.intellij.codeInsight.template.*
import com.intellij.codeInsight.template.macro.MacroBase
import java.util.*

internal class PhpNamespaceMacro : MacroBase {
    constructor() : super("phpNamespace", "phpNamespace()")
    private constructor(name: String, description: String) : super(name, description)

    override fun calculateResult(params: Array<Expression>, context: ExpressionContext, quick: Boolean): Result? {
        var filePath = context.editor
            ?.getVirtualFile()
            ?.getPath()
            ?.replace(context.project.basePath.toString(), "")

        if (null == filePath) {
            return null
        }

        filePath = filePath.substring(0, filePath.lastIndexOf("/"))
        filePath = filePath.replace("/", "\\")
        filePath = filePath.substring(1)

        filePath = filePath.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        }

        return TextResult(filePath)
    }

    override fun isAcceptableInContext(context: TemplateContextType): Boolean {
        return context is PhpContext
    }
}