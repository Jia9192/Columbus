package com.galileo.core.exception;

public class TooBigTransactionResultException extends TronException {

    public TooBigTransactionResultException() { super("too big transaction result"); }

    public TooBigTransactionResultException(String message) { super(message); }
}
