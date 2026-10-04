public class BackyardToolshedRoutine {
    public static void main(String[] args) {
        CuttingTool cuttingTool = new CuttingTool();
        Pruner pruner = new Pruner();

        System.out.println(cuttingTool.use());
        System.out.println(pruner.use());
    }
}

abstract class GardenTool {
    public GardenTool() {
    }

    public abstract String use();

    protected String baseUse() {
        return "Using the tool in the garden";
    }
}

class CuttingTool extends GardenTool {
    public CuttingTool() {
        super();
    }

    @Override
    public String use() {
        return super.baseUse() + ", blade sharpened first";
    }
}

class Pruner extends CuttingTool {
    public Pruner() {
        super();
    }

    @Override
    public String use() {
        return super.use() + ", then trimming branches precisely";
    }
}
