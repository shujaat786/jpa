package atul.backend.jpahibernate.constants;

public class Const {
    public interface Status {
        public static final String SUCCESS = "0000";
        public static final String ERROR = "9999";
        public static final String FAILURE = "0010";
        public static final String ACCESS_DENOED_ERROR_CODE = "9000";
        public static final String ACCESS_DENOED_ERRROR_MSG = "User does not have proper access";
        public static final String UNDEFINED = "9990";

    }

    public interface JWT {
        public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
        public static final String SIGNING_KEY = "devglan123r";
        public static final String TOKEN_PREFIX = "Bearer ";
        public static final String HEADER_STRING = "Authorization";
    }
}