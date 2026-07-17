package com.task.task.errors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // Helper para criar a resposta de erro
    private ErrorResponse createErrorResponse(HttpStatus status, String error, String message, WebRequest request, String specialCode) {
        return new ErrorResponse(
                status.value(),
                error,
                message,
                ((ServletWebRequest) request).getRequest().getRequestURI(),
                specialCode,
                LocalDateTime.now());
    }

    // --- TRATAMENTO DE EXCEÇÕES DE SEGURANÇA (401 e 403) ---

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        log.warn("Acesso negado: {}", ex.getMessage());
        ErrorResponse error = createErrorResponse(
                HttpStatus.FORBIDDEN,
                "Acesso Negado",
                "Você não tem permissão para acessar este recurso.",
                request,
                null);
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        log.warn("Falha na autenticação: {}", ex.getMessage());
        ErrorResponse error = createErrorResponse(
                HttpStatus.UNAUTHORIZED,
                "Falha na Autenticação",
                "Credenciais inválidas ou token expirado/inválido.",
                request,
                null);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
        public ResponseEntity<ErrorResponse> handleInsufficientAuth(InsufficientAuthenticationException ex, WebRequest request) {
       log.warn("Credenciais inválidas: {}", ex.getMessage());
        ErrorResponse error = createErrorResponse(
                HttpStatus.UNAUTHORIZED,
                "Falha na Autenticação",
                "Credenciais inválidas ou token expirado/inválido.",
                request,
                null);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        log.warn("Credenciais inválidas: {}", ex.getMessage());
        ErrorResponse error = createErrorResponse(
                HttpStatus.UNAUTHORIZED,
                "Falha na Autenticação",
                "Credenciais inválidas ou token expirado/inválido.",
                request,
                null);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ErrorResponse> handleDisabledException(DisabledException ex, WebRequest request) {
        log.warn("Conta desabilitada: {}", ex.getMessage());
        ErrorResponse error = createErrorResponse(
                HttpStatus.GONE,
                "Falha na Autenticação",
                "Conta não verificada. Por favor, confirme seu e-mail.",
                request,
                "AUTH_USER_NOT_VERIFIED");
        return new ResponseEntity<>(error, HttpStatus.GONE);
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ErrorResponse> handleLockedException(LockedException ex, WebRequest request) {
        log.warn("Conta bloqueada: {}", ex.getMessage());
        ErrorResponse error = createErrorResponse(
                HttpStatus.UNAUTHORIZED,
                "Falha na Autenticação",
                "Sua conta foi bloqueada administrativamente.",
                request,
                null);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    // --- ERRO DE TAMANHO DE ARQUIVO (Override de ResponseEntityExceptionHandler)
    // ---
    @Override
    protected ResponseEntity<Object> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.warn("Tamanho máximo de upload excedido: {}", ex.getMessage());
        ErrorResponse error = createErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Arquivo Muito Grande",
                "O tamanho do arquivo excede o limite permitido. Tente um arquivo menor.",
                request,
                null);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // --- FALLBACK PARA ERROS INESPERADOS (500) ---

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        log.error("Erro interno inesperado no servidor: ", ex);

        ErrorResponse error = createErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro Interno do Servidor",
                "Ocorreu um erro inesperado. Por favor, tente novamente.",
                request,
                null);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}