package com.sic777.restful.base.response;


/**
 * <p></p>
 *
 * @author Zhengzhenxie<br>
 * <br>2017-12-18
 * @version v1.0
 * @since 1.7
 */
final class Rest405Exception extends AbstractRestException {

    private static final long serialVersionUID = 161657907218141593L;

    /**
     * error
     *
     * @param code
     * @param msg
     */
    Rest405Exception(long code, String msg) {
        super(code, msg, null);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.METHOD_NOT_ALLOWED;
    }

}
