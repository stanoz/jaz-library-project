package com.library.tools.logger.controller;

import com.library.tools.logger.service.ILoggerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("service/logs")
@RequiredArgsConstructor
public class LoggerController {
    private final ILoggerService loggerService;

    @GetMapping(value = "download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity getLogs() throws IOException{
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment;filename="
                        + loggerService.getFileName())
                .contentLength(loggerService.getFileLength())
                .body(loggerService.getLogFile());
    }

}
