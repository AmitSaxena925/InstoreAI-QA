package com.capillary.VisitorMatrix.api.test.heatMap;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTest {
	public static void main(String[] args)
	{
		Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
		System.out.println(Paths.get("").toAbsolutePath().normalize().toString()); 
//		System.out.println(path.toString());
	}
}
