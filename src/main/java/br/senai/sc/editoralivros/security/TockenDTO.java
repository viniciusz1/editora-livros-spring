package br.senai.sc.editoralivros.security;

import lombok.Data;
import lombok.NonNull;

@Data
public class TockenDTO {
    @NonNull
    private String  tipo;
    @NonNull
    private String token;
}
