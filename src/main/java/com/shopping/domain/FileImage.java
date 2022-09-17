package com.shopping.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.UUID;

@Embeddable
@Getter @Setter
public class FileImage {

    private String physicalName;
    private String logicalName;

    protected FileImage() {
    }

    public FileImage(String logicalName) {
        this.physicalName = createPhysicalName(logicalName);
        this.logicalName = logicalName;
    }

    private String createPhysicalName(String logicalName) {
        return UUID.randomUUID() + "." + logicalName.substring(logicalName.lastIndexOf(".") + 1);
    }

}
