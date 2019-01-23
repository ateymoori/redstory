package teymoori.red.story.utils.base

class RestHandler<T> private constructor(val status: RestHandler.Status, val data: T?, val exception: String?) {
    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {
        fun <T> success(data: T?): RestHandler<T> {
            return RestHandler(Status.SUCCESS, data, null)
        }

        fun <T> error(exception: String?): RestHandler<T> {
            return RestHandler(Status.ERROR, null, exception)
        }

        fun <T> loading(data: T?): RestHandler<T> {
            return RestHandler(Status.LOADING, data, null)
        }
    }
}