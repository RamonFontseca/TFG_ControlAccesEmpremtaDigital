package Model;
import com.digitalpersona.onetouch.*;

public class Fingerprint {

    private String name;
    private int remainingUses;
    private boolean enabled;
    private byte[] fingerprintImage;
    private DPFPTemplate template;
    private String status;

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

    public Fingerprint(String name, int remainingUses, boolean enabled, DPFPTemplate template)
    {
        this.name = name;
        this.remainingUses = remainingUses;
        this.enabled = enabled;
        this.template = template;
    }

    public void changeState()
    {
        this.enabled = !this.enabled;
    }

    public String getName(){
        return name;
    }

    public int getRemainingUses(){
        return remainingUses;
    }

    public boolean getEnabled()
    {
        return enabled;
    }

    public String getStatus()
    {
        if (this.enabled) return "HABILITADA";
        else return "DESHABILITADA";
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

    @Override
    public boolean equals(Object object)
    {
        if (object == null) return false;
        if (object.getClass() != this.getClass()) return false;

        final Fingerprint fp = (Fingerprint) object;
        return (this.name.equals(fp.name) && this.enabled == fp.enabled && fp.remainingUses == fp.remainingUses);
    }
}
