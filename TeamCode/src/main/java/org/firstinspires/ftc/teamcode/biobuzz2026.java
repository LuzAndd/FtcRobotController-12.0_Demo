package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "biobuzz2026", group = "Iterative Opmode")
public class biobuzz2026 extends OpMode {

    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor intake;

    @Override
    public void init() {
        // Configuración de motores del chasis (Tanque)
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");

        // En un chasis tanque, los motores están montados opuestos.
        // Invertimos la dirección de uno para que ambos avancen en el mismo sentido.
        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);

        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Configuración del motor Intake
        intake = hardwareMap.get(DcMotor.class, "intake");
        intake.setDirection(DcMotorSimple.Direction.FORWARD);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        // Control Tanque / Arcade:
        double leftPowerDrive = -gamepad1.left_stick_y;
        double rightPowerTurn = -gamepad1.right_stick_x;
        double leftPower = Range.clip(leftPowerDrive + rightPowerTurn, -1.0, 1.0);
        double rightPower = Range.clip(leftPowerDrive - rightPowerTurn, -1.0, 1.0);

        leftDrive.setPower(leftPower);
        rightDrive.setPower(rightPower);

        // Control del motor Intake con el botón X del gamepad
        if (gamepad1.x) {
            intake.setPower(1.0);
        } else {
            intake.setPower(0.0);
        }

        // Telemetría en la Driver Station
        telemetry.addData("Left Drive Power", leftPower);
        telemetry.addData("Right Drive Power", rightPower);
        telemetry.addData("Intake Power", intake.getPower());
    }
}

