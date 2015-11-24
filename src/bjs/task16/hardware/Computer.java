package bjs.task16.hardware;

import bjs.task16.os.*;
import bjs.task16.applications.*;

/**
 * Created by YM on 31.10.2015.
 * Class Computer presents a computer
 */

public class Computer {
	/**The type of the computer. E.g. "PC", "Notebook", etc.*/
	private String computerType;
	/**The type of the CPU. E.g. "Intel", "ARM", etc.*/
	private String processorType;

	/**The CPU clock rate in GHz*/
    private double processorClockRate;
	/**Random access memory size in Gb*/
    private double randomAccessMemorySize;
	/**Size of the fixed memory storage in Gb. E.g. hard-drive*/
	private double fixedMemorySize;

	/**Current operation system*/
	private OperatingSystem operatingSystem;

	/**Flag to indicate whether power is on*/
	boolean isPowerOn;

	/**
	 * Implements "power on" functionality.
	 */
	public void powerOn(){
		isPowerOn = true;

		setComputerType("PC");
		setProcessorType("Intel Core i5-2400 CPU");
		setProcessorClockRate(3.10);
		setRandomAccessMemorySize(8.00);

		System.out.println("Power is on.\n");
    }

	/**
	 * Implements "power off" functionality
	 */
	public void powerOff(){
		isPowerOn = false;

		if (operatingSystem != null)
			operatingSystem.shutDown();

		operatingSystem = null;
		System.out.println("Computer power is off.");
	}

	/**
	 * Installs operating system
	 */
	public void installOperationSystem(String operatingSystem) {
		//Create partitions and format fixed memory
		setFixedMemorySize(1000);

		//Start installation of the operating system
		this.operatingSystem = new OperatingSystem(this);

		//Check system requirements
		if (this.operatingSystem.checkSystemRequirements()) {
			System.out.println("Hardware complies with the system requirements.");
		}
		else {
			System.out.println("Hardware does not comply with the system requirements. Installation aborted.");
			return;
		}

		this.operatingSystem.setOperationSystem(operatingSystem);
		this.operatingSystem.setComputerName("MyComputer");

		System.out.println("Operating system " + this.operatingSystem.getOperationSystem() + " successfully installed.\n");
	}

	/**
	 * Installs set of applications
	 */
	public void installApplications() {
		Calculator calculator = new Calculator();
		operatingSystem.installApplication(calculator);

		FileManager fileManager = new FileManager(true);
		operatingSystem.installApplication(fileManager);

		System.out.print(operatingSystem.toString());
	}

	/**
	 * @return Calculator object
     */
	public Calculator getCalculator() throws ComputerAccessException {
		if (!isPowerOn)
			throw new ComputerAccessException("You cannot use this program because computer is off.");

		return (Calculator)operatingSystem.getApplication("Calculator v0.1");
	}

	/**
	 * @return FileManager object
	 */
	public FileManager getFileManager() throws ComputerAccessException {
		if (!isPowerOn)
			throw new ComputerAccessException("You cannot use this program because computer is off.");

		return (FileManager)operatingSystem.getApplication("File Manager v0.1");
	}

    @Override
    public String toString() {
        //toString() method defined for all existing Java objects and can be overridden for user-defined objects
		String result = "Computer type: " + computerType + "\n";
        result += "Processor type: " + processorType + "\n";
        
		result += "Processor clock rate: " + getProcessorClockRate() + "\n";
        result += "Random access memory size: " + getRandomAccessMemorySize()  + "\n";
		result += "Fixed memory size: " + getFixedMemorySize() + "\n\n";

        return result;
    }

	/**
	 * Sets computer type
	 * @param computerType The type of the computer.
     */
	public void setComputerType(String computerType) {
		this.computerType = computerType;
	}

	/**
	 * Sets processor type
	 * @param processorType Type of the processor
     */
	public void setProcessorType(String processorType) {
		this.processorType = processorType;
	}

	/**
	 * Sets clock rate
	 * @param processorClockRate Current clock rate in GHz
     */
	public void setProcessorClockRate(double processorClockRate) {
		if (processorClockRate > 0) {
			this.processorClockRate = processorClockRate;
		}
	}

	/**
	 * Set ramdom access memory size.
	 * @param randomAccessMemorySize Size of the random access memory in Gb
     */
	public void setRandomAccessMemorySize(double randomAccessMemorySize) {
		if (randomAccessMemorySize > 0)
			this.randomAccessMemorySize = randomAccessMemorySize;
	}

	/**
	 * Sets fixed memory size
	 * @param fixedMemorySize Fixed memory size in Gb
     */
	public void setFixedMemorySize(double fixedMemorySize) {
		this.fixedMemorySize = fixedMemorySize;
	}

	/**
	 * @return Computer type
     */
	public String getComputerType() {
		return computerType;
	}

	/**
	 * @return Processor type
	 */
	public String getProcessorType() {
		return processorType;
	}

	/**
	 * @return Processor clock rate
	 */
	public double getProcessorClockRate() {
		return processorClockRate;
	}

	/**
	 * @return Random access memory size in Gb
	 */
	public double getRandomAccessMemorySize() {
		return randomAccessMemorySize;
	}

	/**
	 * @return Fixed memory size in Gb
	 */
	public double getFixedMemorySize() {
		return fixedMemorySize;
	}
}
