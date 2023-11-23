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

        val path = file.getParent().getName().lowercase()

        var filename = Regex("Command$").replace(file.getNameWithoutExtension(), "")
        filename = Regex("([A-Z])").replace(filename, "-$1")
        filename = filename.lowercase()
        filename = Regex("^-").replace(filename, "")

        return TextResult(path + ":" + filename)
    }

    override fun isAcceptableInContext(context: TemplateContextType): Boolean {
        return context is PhpContext
    }
}