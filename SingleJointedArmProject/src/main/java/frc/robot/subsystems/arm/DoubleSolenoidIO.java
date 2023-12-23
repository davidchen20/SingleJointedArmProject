package frc.robot.subsystems.arm;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public interface DoubleSolenoidIO {
    @AutoLog
    public static class DoubleSolenoidIOInputs {
        public Value dsValue = Value.kOff;
    }

    public void updateInputs(DoubleSolenoidIOInputs inputs);

    public void setValue(Value value);
}
