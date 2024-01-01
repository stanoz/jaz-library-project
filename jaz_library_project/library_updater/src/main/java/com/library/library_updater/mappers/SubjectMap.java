package com.library.library_updater.mappers;

import com.library.library_data.model.Subject;
import org.springframework.stereotype.Component;

@Component
public class SubjectMap implements IMap<String, Subject>{
    @Override
    public Subject mapToEntity(String name) {
        Subject subjectEntity = new Subject();
        subjectEntity.setName(name);
        return subjectEntity;
    }
}
