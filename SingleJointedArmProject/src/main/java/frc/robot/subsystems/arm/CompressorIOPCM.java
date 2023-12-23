package frc.robot.subsystems.arm;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class CompressorIOPCM implements CompressorIO{
    private final Compressor compressor;

    public CompressorIOPCM(int module) {
        compressor  = new Compressor(module, PneumaticsModuleType.CTREPCM);
    }
    @Override
    public void updateInputs(CompressorIOInputs inputs) {
        inputs.isOn = compressor.isEnabled();
        inputs.compressorPressure = compressor.getPressure();
    }

    @Override
    public void enableCompressor() {
        compressor.enableDigital();
    }

    @Override
    public void disableCompressor() {
        compressor.disable();
    }

}