package frc.robot.subsystems.arm;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class DoubleSolenoidIOPCM implements DoubleSolenoidIO {
    private final DoubleSolenoid ds;
    
    public DoubleSolenoidIOPCM(int module, int fChannel, int rChannel) {
         ds = new DoubleSolenoid(module, PneumaticsModuleType.CTREPCM, fChannel, rChannel);
    }
    @Override
    public void updateInputs(DoubleSolenoidIOInputs inputs) {
        inputs.dsValue = ds.get();
    }

    @Override
    public void setValue(Value value) {
        ds.set(value);
    }
    
}
