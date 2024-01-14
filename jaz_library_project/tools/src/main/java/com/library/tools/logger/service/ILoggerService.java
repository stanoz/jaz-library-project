package com.library.tools.logger.service;

import org.springframework.core.io.InputStreamResource;

import java.io.IOException;

public interface ILoggerService {
    InputStreamResource getLogFile() throws IOException;
    long getFileLength();
    String getFileName();
}
