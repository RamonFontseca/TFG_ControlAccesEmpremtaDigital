package Model;
import com.digitalpersona.onetouch.*;

public class Fingerprint {

    private String name;
    private int remainingUses;
    private boolean enabled;
    private byte[] fingerprintImage;
    private DPFPTemplate template;

    public Fingerprint()
    {
        name = null;
        remainingUses = 0;
        enabled = false;
        fingerprintImage = null;
        template = null;
    }

    public Fingerprint(Fingerprint fp)
    {
        this.name = fp.name;
        this.remainingUses = fp.remainingUses;
        this.enabled = fp.enabled;
        this.fingerprintImage = fp.fingerprintImage;
        this.template = fp.template;
    }

    public DPFPTemplate GetTemplate()
    {
        return template;
    }

    public void SetName(String value)
    {
        this.name = value;
    }

    public void SetRemainingUses(int value)
    {
        this.remainingUses = value;
    }

    public void SetTemplate(DPFPTemplate template)
    {
        this.template = template;
    }

}
