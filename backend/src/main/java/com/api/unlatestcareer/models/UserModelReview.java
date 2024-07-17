package com.api.unlatestcareer.models;

import lombok.Data;

@Data
public class UserModelReview {
    private int id;
    private String username;
    private String role;
    private boolean enabled;
}
