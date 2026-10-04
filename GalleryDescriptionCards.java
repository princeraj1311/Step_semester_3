public class GalleryDescriptionCards {
    public static void main(String[] args) {
        Painting painting = new Painting("Sunset Fields");
        Sculpture sculpture = new Sculpture("The Thinker II");

        System.out.println(painting.describe());
        System.out.println(painting.getPieceId());
        System.out.println(sculpture.describe());
        System.out.println(sculpture.getPieceId());
    }
}

abstract class ArtPiece {
    private static int nextPieceId = 1;
    private final String pieceId;

    protected ArtPiece() {
        pieceId = String.valueOf(nextPieceId++);
    }

    public abstract String describe();

    public String getPieceId() {
        return pieceId;
    }
}

class Painting extends ArtPiece {
    private final String title;

    public Painting(String title) {
        this.title = title;
    }

    @Override
    public String describe() {
        return "Painting: " + title + ", framed on canvas";
    }
}

class Sculpture extends ArtPiece {
    private final String title;

    public Sculpture(String title) {
        this.title = title;
    }

    @Override
    public String describe() {
        return "Sculpture: " + title + ", carved from stone";
    }
}
