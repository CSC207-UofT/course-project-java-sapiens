package entities;

public class NoteCommodityAdaptor implements Notable{
    /*
     * An adapter object that allows additional information to be added to the commodity.
     */
    private final Commodity commodity;
    private Type type;
    private String note;

    private enum Type {
        EasyToBreak {
            @Override
            public String toString() {
                return "Easy to break, please handle with care.";
            }
        },
        Pet {
            @Override
            public String toString() {
                return "Pet, please give sufficient air flow.";
            }
        },
    }

    public NoteCommodityAdaptor(Commodity commodity) {
        this.commodity = commodity;
    }

    @Override
    public void markEasyToBreak() {
        this.type = Type.EasyToBreak;
    }

    @Override
    public void markPet() {
        this.type = Type.Pet;
    }

    public Type getType() {
        return this.type;
    }

    @Override
    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String getNote() {
        return this.note;
    }

    public Commodity getCommodity() {
        return this.commodity;
    }
}
