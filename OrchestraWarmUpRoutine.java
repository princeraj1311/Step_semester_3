public class OrchestraWarmUpRoutine {
    public static void main(String[] args) {
        StringInstrument stringInstrument = new StringInstrument();
        Violin violin = new Violin();

        System.out.println(stringInstrument.play());
        System.out.println(violin.play());
    }
}

abstract class Instrument {
    public Instrument() {
    }

    public abstract String play();

    protected String basePlay() {
        return "Strumming the strings";
    }
}

class StringInstrument extends Instrument {
    public StringInstrument() {
        super();
    }

    @Override
    public String play() {
        return super.basePlay();
    }
}

class Violin extends StringInstrument {
    public Violin() {
        super();
    }

    @Override
    public String play() {
        return super.play() + ", with a bow drawn across four strings";
    }
}
