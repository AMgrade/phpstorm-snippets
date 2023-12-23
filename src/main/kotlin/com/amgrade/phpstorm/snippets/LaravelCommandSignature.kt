package com.amgrade.phpstorm.snippets

import com.intellij.codeInsight.template.*
import com.intellij.codeInsight.template.macro.MacroBase

internal class LaravelCommandSignature : MacroBase {
    constructor() : super("laravelCommandSignature", "laravelCommandSignature()")
    private constructor(name: String, description: String) : super(name, description)

    override fun calculateResult(params: Array<Expression>, context: ExpressionContext, quick: Boolean): Result? {
        val file = context.editor?.getVirtualFile()

        if (null == file) {
            return null
        }

        val path = this.toKebabCase(file.getParent().getName())
        val filename = this.toKebabCase(Regex("Command$").replace(file.getNameWithoutExtension(), ""))

        return TextResult(path + ":" + filename)
    }

    override fun isAcceptableInContext(context: TemplateContextType): Boolean {
        return context is PhpContext
    }

    private fun toKebabCase(string: String): String {
        var modified = Regex("([A-Z])").replace(string, "-$1")
        modified = modified.lowercase()
        modified = Regex("^-").replace(modified, "")

        return modified
    }
}