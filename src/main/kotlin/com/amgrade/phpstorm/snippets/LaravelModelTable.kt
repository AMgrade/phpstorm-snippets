package com.amgrade.phpstorm.snippets

import com.intellij.codeInsight.template.*
import com.intellij.codeInsight.template.macro.MacroBase

internal class LaravelModelTable : MacroBase {
    constructor() : super("laravelModelTable", "laravelModelTable()")
    private constructor(name: String, description: String) : super(name, description)

    override fun calculateResult(params: Array<Expression>, context: ExpressionContext, quick: Boolean): Result? {
        val file = context.editor?.getVirtualFile()

        if (null == file) {
            return null
        }

        var filename = Regex("([A-Z])").replace(file.getNameWithoutExtension(), "_$1")
        filename = filename.lowercase()
        filename = Regex("^_").replace(filename, "")

        if (filename.endsWith("es")) {
            return TextResult(filename)
        }

        if (filename.endsWith("is")) {
            return TextResult(Regex("is$").replaceFirst("es", filename))
        }

        if (filename.endsWith("y")) {
            return TextResult(Regex("y$").replaceFirst("ies", filename))
        }

        if (
            filename.endsWith("ch") ||
            filename.endsWith("x") ||
            filename.endsWith("sh") ||
            filename.endsWith("s") ||
            filename.endsWith("o")
        ) {
            return TextResult(filename + "es")
        }

        return TextResult(filename + "s")
    }

    override fun isAcceptableInContext(context: TemplateContextType): Boolean {
        return context is PhpContext
    }
}