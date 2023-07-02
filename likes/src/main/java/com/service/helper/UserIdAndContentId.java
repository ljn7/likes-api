package com.service.helper;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude
@Getter
@Setter
@Data
@NoArgsConstructor
public class UserIdAndContentId {
    
    public String userId;
    public String contentId;
}
