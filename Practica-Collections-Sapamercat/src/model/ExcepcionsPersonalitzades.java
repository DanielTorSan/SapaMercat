package model;

public class ExcepcionsPersonalitzades extends Exception {
    public ExcepcionsPersonalitzades(String message) {
        super(message);
    }

}
class LimitCaracteresException extends Exception {
    public LimitCaracteresException(String message) {
        super(message);
    }
}

class NegatiuException extends Exception {
    public NegatiuException(String message) {
        super(message);
    }
}

class DataCaducitatException extends Exception {
    public DataCaducitatException(String message) {
        super(message);
    }
}

class LimitProductesException extends Exception {
    public LimitProductesException(String message) {
        super(message);
    }
}

class EnumFailException extends Exception {
    public EnumFailException(String message) {
        super(message);
    }
}