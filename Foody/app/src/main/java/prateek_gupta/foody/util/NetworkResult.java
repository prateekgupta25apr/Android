package prateek_gupta.foody.util;

public class NetworkResult<T> {
    T data ;
    String message;

    public NetworkResult() {
    }

    public NetworkResult(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public static class Success<T> extends NetworkResult<T>{
        T data;

        public Success(T data) {
            super(data, null);
            this.data = data;
        }
    }

    public static class Error<T> extends NetworkResult<T>{
        public Error( String message) {
            super(null, message);
        }
    }

    public static class Loading<T> extends NetworkResult<T>{
        public Loading() {
        }
    }
}
