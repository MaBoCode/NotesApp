package fr.notes.core.util;

public abstract class DataState<D> {

    public DataState<D> value;

    private DataState() {

    }

    public final static class Success<D> extends DataState<D> {
        public D data;

        public Success(D data) {
            this.data = data;
        }
    }

    public final static class Error<D> extends DataState<D> {
        public Exception exception;

        public Error(Exception e) {
            this.exception = e;
        }
    }

}
