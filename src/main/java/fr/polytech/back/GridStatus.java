package fr.polytech.back;

public enum GridStatus {
    piece_creation("PE"),
    active_piece("AP"),
    inactive_piece("IP");

    private String code;

    private GridStatus(String code){
        this.code = code;
    }
}
