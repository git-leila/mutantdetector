package com.detector.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detector.dao.DnaDao;
import com.detector.domain.Estadisticas;
import com.detector.entity.Dna;

@Service
public class DetectorServiceImpl implements DetectorService {
	@Autowired
	private DnaDao dnaDAO;
	public static int encontrado;
	
	@Override
	public boolean isMutant(Dna dna) {
		encontrado=0;
		String[][] matrizDna=completarMatriz(dna);
		
	    if(busquedaHorizontal(dna)){
	     	return true;
	    }else {	    	
	    	if(busquedaVertical(matrizDna)){
	    		return true;
	    	}
	    	else if(busquedaOblicua(matrizDna)){
	    		return true;		
	    	}
	    }
	    return false;
	}
	
	public static boolean busquedaHorizontal(Dna dna) {
	    int size = dna.length();
	    for(int i=0; i<size; i++){
	        String str = dna.getFila(i);
	        if(str.contains("AAAA")){
	        	encontrado++;
	        	if(encontrado==2)
	          		return true;
	        }
	        else if(str.contains("TTTT")){
				encontrado++;
	        	if(encontrado==2)
	          		return true;
	        }
	        else if(str.contains("CCCC")){
				encontrado++;
	        	if(encontrado==2)
	          		return true;
	        }
	        else if(str.contains("GGGG")){
				encontrado++;
	        	if(encontrado==2)
	          		return true;
	        }
	    }    
	    return false;
	}

	public static boolean busquedaVertical(String[][] arr) {  	
	    for(int col=0;col<arr.length;col++){
	      int ocurrencia=1;
	      for(int row =0; row<arr.length-1;row++){
	        
	        if(arr[row][col].equals(arr[row+1][col])){
	          ocurrencia++;
	          if(ocurrencia==4){//encuentra palabra
	         	encontrado++;
	        	if(encontrado==2)
	          		break;
	          }            
	        }
	        else ocurrencia=1;
	      }

	      if(encontrado==2){
	        return true;
	      }
	    }
	    return false;    
	    
	}
	
	  public static boolean busquedaOblicua(String[][] arr) {		  	
		  	int k=0;
		    while(k<=(arr.length-4)){
		      int i=k;
		      int j=0;
		      int ocurrencia_inf=1;
		      int ocurrencia_sup=1;

		      while(i<(arr.length-1) && j<(arr.length-1)){
		        //principal inferior
				if(arr[i][j].equals(arr[i+1][j+1])){            
					ocurrencia_inf++;
		          	if(ocurrencia_inf==4){//encuentra palabra
		         		encontrado++;
		        		if(encontrado==2)
		          			break;
		          	}            
		        }
		        else ocurrencia_inf=1;
		        			
		        //principal superior
		        if(i!=j){ //no evalua la diagonal principal porque evaluo anteriormente
			        if(arr[j][i].equals(arr[j+1][i+1])){            
						ocurrencia_sup++;            
						if(ocurrencia_sup==4){//encuentra palabra
							encontrado++;
						    if(encontrado==2)
						    	break;
						}            
					}
					else ocurrencia_sup=1;
				}
		        i++;
		        j++;
		      }	//end while
		      if(encontrado==2){
		        return true;
		      }
		      k++;
		    }

			//secundaria inferior (abajo -> arriba)
		    int s=0;
		    while(s<=(arr.length-4)){
		      int r=s;
		      int t=arr.length-1;
		      int ocurrencia_sec_inf=1;
		      while(t>0 && r<(arr.length-1)){
		      	if(arr[t][r].equals(arr[t-1][r+1])){
		        	ocurrencia_sec_inf++;            
		          if(ocurrencia_sec_inf==4){//encuentra palabra
		            encontrado++;
		            if(encontrado==2)
		              break;
		          }            
		        }
		        else ocurrencia_sec_inf=1;                                   
		        r++;
		        t--;
		      }      
		      if(encontrado==2){
		        return true;
		      }
		      s++;
		    }

		    //secundaria superior (abajo hacia arriba)
		    int m=arr.length-2;//no tomo la diagonal secundaria porque ya la tome anteriormente
		    while(m>=3){  //si es menor que ya no encontrará secuencia    
		      int n=m;
		      int u=0;
		      int ocurrencia_sec_sup=1;
		      while(u<(arr.length-1) && n>0){        
		        if(arr[n][u].equals(arr[n-1][u+1])){
		        	ocurrencia_sec_sup++;            
		          if(ocurrencia_sec_sup==4){//encuentra palabra
		            encontrado++;
		              if(encontrado==2)
		                break;
		          }            
		        }
		        else ocurrencia_sec_sup=1;                                   
		        u++;
		        n--;        
		      }
		      if(encontrado==2){
		        return true;
		      }
		      m--;
		    }	   
		  return false;
	  }	

	public static String[][] completarMatriz( Dna matriz) {	   
	    int size=matriz.length();
	    String[][] arr;
	    arr= new String[size][size];
	    for (int fila = 0; fila < size; ++fila) {
	          String[] a=matriz.getFila(fila).split("");
	            for (int columna = 0; columna < a.length; ++columna) {
	                arr[fila][columna] = a[columna];	                
	            }
	        }
	    return arr;
	  }
	
	@Override
	public void save(Dna dna) {
		dnaDAO.save(dna);
	}
	
	@Override
	public Estadisticas findStats() {
		
		long cant_mutant=dnaDAO.countMutant(1);
		long cant_human=dnaDAO.countMutant(0);
		float ratio=cant_mutant/cant_human;
		Estadisticas e = new Estadisticas((int)cant_mutant, (int)cant_human, ratio);
		
		return e;
	}
}
