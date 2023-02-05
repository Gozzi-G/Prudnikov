package com.pr.moviekp.data.utils.errors

open class ApplicationException : RuntimeException {

    constructor()

    constructor(message: String) : super(message)

    constructor(cause: Throwable) : super(cause)

    constructor(message: String, cause: Throwable) : super(message, cause)

}
