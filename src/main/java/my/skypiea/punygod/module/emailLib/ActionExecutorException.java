package my.skypiea.punygod.module.emailLib;

/**
 * *****************************
 * **      Just For           **
 * **             Puny God    **
 * *****************************
 * Created by Reborn on 2016/3/8.
 */
/**
 * ActionExecutor exception. <p/> The exception provides information regarding the transient/no-transient/fatal nature
 * of the exception.
 */
public class ActionExecutorException extends Exception {

    /**
     * Enum that defines the type of error an  has produced.
     */
    public static enum ErrorType {

        /**
         * The action will be automatically retried by Oozie.
         */
        TRANSIENT,

        /**
         * The job in set in SUSPEND mode and it will wait for an admin to resume the job.
         */

        NON_TRANSIENT,

        /**
         * The action completes with an error transition.
         */
        ERROR,

        /**
         * The action fails. No transition is taken.
         */
        FAILED
    }

    private ErrorType errorType;
    private String errorCode;

    /**
     * Create an action executor exception.
     *
     * @param errorType the error type.
     * @param errorCode the error code.
     * @param message the error message.
     */
    public ActionExecutorException(ErrorType errorType, String errorCode, String message) {
        super(message);
/*        this.errorType = ParamChecker.notNull(errorType, "errorType");
        this.errorCode = ParamChecker.notEmpty(errorCode, "errorCode");*/

        this.errorType = errorType;
        this.errorCode = errorCode;
    }

    /**
     * Create an action executor exception.
     *
     * <p/> If the last parameter is an Exception it is used as the exception cause.
     *
     * @param errorType the error type.
     * @param errorCode the error code.
     * @param messageTemplate the error message.
     * @param params parameters used to create the exception message together with the messageTemplate. If the last
     * parameter is an Exception it is used as the exception cause.
     */
    public ActionExecutorException(ErrorType errorType, String errorCode, String messageTemplate, Object... params) {
/*        this.errorType = ParamChecker.notNull(errorType, "errorType");
        this.errorCode = ParamChecker.notEmpty(errorCode, "errorCode");*/

        this.errorType = errorType;
        this.errorCode = errorCode;
    }

    /**
     * Return the error type of the exception.
     *
     * @return the error type of the exception.
     */
    public ErrorType getErrorType() {
        return errorType;
    }

    /**
     * Return the error code of the exception.
     *
     * @return the error code of the exception.
     */
    public String getErrorCode() {
        return errorCode;
    }
}
