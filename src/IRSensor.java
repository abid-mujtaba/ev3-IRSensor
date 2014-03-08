/*
 *  Copyright 2014 Abid Hasan Mujtaba
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;


/**
 * Main class of project.
 *
 * The project tests the IR Sensor on the EV3 reporting the values read in.
 */

public class IRSensor
{
    private static SampleProvider sampler;


    public static void main(String[] args)
    {
        log("Program Started.");

        initialize();


        float[] sample = new float[sampler.sampleSize()];

        for (int ii = 0; ii < 10; ii++)         // We take 10 samples at 2 seconds intervals and log them
        {
            sampler.fetchSample(sample, 0);

            log("Sample: " + sample[0]);

            Delay.msDelay(2000);
        }

        log("Program Exiting");
    }


    private static void initialize()       // Initialization routine for the class
    {
        Port port = LocalEV3.get().getPort("S4");           // Define the port used by the Sensor
        SensorModes sensor = new EV3IRSensor(port);         // Initiate the EV3 IR Sensor

        sampler = sensor.getMode("Distance");               // Define the sampler to be the IRSensor in "Distance" measuring mode
    }


    private static void log(String message)         // Method for printing log messages to stdout
    {
        System.out.println("log>\t" + message);     // We add the text at the beginning to differentiate the log from the other text being printed on the screen
    }
}
