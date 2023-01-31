package Sokoban;

public class SokobanExceptions {
    public static class InvalidLevelException extends Exception{
        public InvalidLevelException(String message){
            super(message);
        }
    };

    public static class SokobanRuntimeException extends Exception{
        public SokobanRuntimeException(String message){
            super(message);
        }
    };
}
