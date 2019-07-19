package com.minip.tx.utils;


public class Result<T>
{
    // error_code 状态值：0 为成功，其他数值代表失败
    private Integer code;

    // error_msg 错误信息，若code为0时，为success
    private String msg;

    // content 返回体报文的出参，使用泛型兼容不同的类型
    private T result;

    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public T getResult()
    {
        return result;
    }

    public void setResult(T result)
    {
        this.result = result;
    }

    public Result putResult(T result)
    {
        setResult(result);
        return this;
    }
    /**
     * 返回成功，传入返回体具体出參
     * @param object
     * @return
     */
    public static Result ok(Object object)
    {
        Result response = new Result();
        response.setCode(0);
        response.setMsg("success");
        response.setResult(object);
        return response;
    }
    public static Result ok()
    {
        return ok(null);
    }


    public static Result error(ErrorTypeEnum errorTypeEnum){
        Result response = new Result();
        response.setCode(errorTypeEnum.getCode());
        response.setMsg(errorTypeEnum.getCause());
        return response;
    }

    @Override
    public String toString()
    {
        return "Result{" + "code=" + code + ", msg='" + msg + '\'' + ", result=" + result + '}';
    }

}

