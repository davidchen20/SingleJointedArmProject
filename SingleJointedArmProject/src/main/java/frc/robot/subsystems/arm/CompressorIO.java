package frc.robot.subsystems.arm;

import org.littletonrobotics.junction.AutoLog;

public interface CompressorIO {
    @AutoLog
    public static class CompressorIOInputs {
        public boolean isOn = false;
        public double compressorPressure = 0;
    }

    public void updateInputs(CompressorIOInputs inputs);

    public void enableCompressor();

    public void disableCompressor();
}
