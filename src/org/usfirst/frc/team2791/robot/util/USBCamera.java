package org.usfirst.frc.team2791.robot.util;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.CameraServer;


/**
 * Custom USB Camera class used to automate certain features of WPI's UsbCamera
 * 
 */
public class USBCamera extends UsbCamera{

	/**
	 * Default resolution of the camera incase it can't handle a requested res
	 */
	private Resolution defaultRes;

	private String camName = "";

	
	/**
	 * Constructor 
	 * 
	 * @param name name of Camera
	 * @param dev USB port
	 * @throws Exception thrown if capturing or pixel formation fails
	 */
	public USBCamera(String name, int dev) throws Exception {
		super("Gear Camera", 1);
		camName = name;
		
		setDefaultResolution(Resolution.k480x360, 0.25);
		
		try {
			CameraServer.getInstance().startAutomaticCapture();
			this.setPixelFormat(PixelFormat.kMJPEG);
		}catch(Exception e) {
			throw new Exception(camName + " USBCamera Failed to start");
		}
	}

	/**
	 * @param res default Resolution
	 */
	public void setDefaultResolution(Resolution res) {
		defaultRes = res;
	}
	
	/**
	 * 
	 * @param res default resolution
	 * @param scale factor to scale resoltion
	 */
	public void setDefaultResolution(Resolution res, double scale) {
		res.scale(scale);
		defaultRes = res;
	}

	/**
	 * 
	 * @param res resolution to set camera capture to
	 * @throws Exception if resolution cannot be set
	 */
	public void setResolution(Resolution res) throws Exception{
		int resX = res.getResolution()[0];
		int resY = res.getResolution()[1];

		try {
			if(!super.setResolution(resX, resY)){
				
				resX = defaultRes.getResolution()[0];
				resY = defaultRes.getResolution()[1];
				
				super.setResolution(resX, resY);//default value if the other resolution does not work
				System.out.println("******Desired resolution FAILED for" + camName + "*****");
				System.out.println("      Switching to Default Res");
			}
		}catch(Exception e){
			throw new Exception(camName + "Resolution cannot be set");
		}
	}

	/**
	 * 
	 * @param res resolution to set camera capture to
	 * @param scaleFactor factor to scale resoltion
	 * @throws Exception if resolution cannot be set
	 */
	public void setResolution(Resolution res, double scaleFactor) throws Exception {
		res.scale(scaleFactor);
		int resX = res.getResolution()[0];
		int resY = res.getResolution()[1];

		try {
			if(!super.setResolution(resX, resY)){
				
				resX = defaultRes.getResolution()[0];
				resY = defaultRes.getResolution()[1];
				
				super.setResolution(resX,resY);//default value if the other resolution does not work
				System.out.println("******Desired resolution FAILED for" + camName + "*****");
				System.out.println("      Switching to Default Res");

			}
		}catch(Exception e) {
			throw new Exception(camName + "Resolution cannot be set");
		}
	}


	/**
	 * A variable holder class to make sure that the resolutions being set for cameras are valid ratios
	 *
	 */
	public enum Resolution{
		
		/**
		 * These are all tested resolutions that can be set w/o error
		 */
		k1024x768(1024,768),
		k640x480(640,480), // 320x240, 160x120
		k640x360(640,360), // 320x180, 160x90
		k480x360(480,360); // 240x180, 120x90 

		private int resX, resY;

		private Resolution(int x, int y) {
			resX = x;
			resY = y;
		}

		public void scale(double factor) {
			resX *= factor;
			resY *= factor;
		}

		public int[] getResolution(){

			int[] resolutions = {resX, resY};
			return resolutions;
		}		
	}
}
