package Model;

public class Fingerprint {

    private String name;
    private int remainingUses;
    private boolean enabled;
    private byte[] fingerprintImage;

    public Fingerprint()
    {
        name = null;
        remainingUses = 0;
        enabled = false;
        fingerprintImage = null;
    }

    public Fingerprint(Fingerprint fp)
    {
        name = fp.name;
        remainingUses = fp.remainingUses;
        enabled = fp.enabled;
        fingerprintImage = fp.fingerprintImage;
    }
}
