package com.teamper.ui.event;

import com.teamper.ui.module.login.model.dto.CredentialUIDto;

public class UILoginEvent extends UIEvent {
    private CredentialUIDto credentialUIDto;

    public CredentialUIDto getCredentialUIDto() {
        return credentialUIDto;
    }

    public void setCredentialUIDto(CredentialUIDto credentialUIDto) {
        this.credentialUIDto = credentialUIDto;
    }
}
