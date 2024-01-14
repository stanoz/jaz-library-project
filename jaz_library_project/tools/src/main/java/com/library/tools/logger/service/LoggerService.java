package com.library.tools.logger.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class LoggerService implements ILoggerService{
    private final File file;
    @Override
    public InputStreamResource getLogFile() throws IOException {
        return new InputStreamResource(new FileInputStream(file));
    }

    @Override
    public long getFileLength() {
        return file.length();
    }

    @Override
    public String getFileName() {
        return file.getName();
    }
}
