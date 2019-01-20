package teymoori.red.story.utils

import io.reactivex.disposables.CompositeDisposable


class RxDisposableManager {


    companion object {
        private var compositeDisposable: CompositeDisposable? = null

        fun getCompositeDisposable(): CompositeDisposable {
            if (compositeDisposable == null || compositeDisposable!!.isDisposed) {
                compositeDisposable = CompositeDisposable()
            }
            return compositeDisposable as CompositeDisposable
        }
    }


}