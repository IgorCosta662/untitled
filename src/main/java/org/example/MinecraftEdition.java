package org.example;

public enum MinecraftEdition {
    JAVA("Java Edition"),
    BEDROCK("Bedrock Edition"),
    BOTH("Ambas as Edições");

    private final String displayName;

    MinecraftEdition(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

