package com.example.demo.Wrapper;


import java.util.HashMap;
import java.util.List;

public class DataQueryCharts {

	private HashMap<String, List<BitacoraDataCount>> dataBitacora;
	
	
	private HashMap<String, List<ChartDataBar>> dataBarChart;

	
	

	public DataQueryCharts() {
		super();
	}




	public DataQueryCharts(HashMap<String, List<BitacoraDataCount>> dataBitacora,
			HashMap<String, List<ChartDataBar>> dataBarChart) {
		super();
		this.dataBitacora = dataBitacora;
		this.dataBarChart = dataBarChart;
	}




	public HashMap<String, List<BitacoraDataCount>> getDataBitacora() {
		return dataBitacora;
	}




	public void setDataBitacora(HashMap<String, List<BitacoraDataCount>> dataBitacora) {
		this.dataBitacora = dataBitacora;
	}




	public HashMap<String, List<ChartDataBar>> getDataBarChart() {
		return dataBarChart;
	}




	public void setDataBarChart(HashMap<String, List<ChartDataBar>> dataBarChart) {
		this.dataBarChart = dataBarChart;
	}

	

	
	
	
	

	
	
	
	
	
}
