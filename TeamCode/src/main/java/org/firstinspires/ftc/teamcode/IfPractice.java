package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class IfPractice extends OpMode {

    @Override
    public void init() {

    }

    @Override
    public void loop() {
        double leftY = gamepad1.left_stick_y;

        if(leftY < 0) {
            telemetry.addData("Left Stick", "is negative");

        }
        else {
            telemetry.addData("Left Stick", "is positive");
        }
        telemetry.addData("Left Stick Value", leftY);
    }

}
