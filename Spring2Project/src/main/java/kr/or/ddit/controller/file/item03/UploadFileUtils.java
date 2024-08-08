package kr.or.ddit.controller.file.item03;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception  {
		// /2024/05/29/UUID_원본 파일명
		UUID uuid = UUID.randomUUID();
		
		// UUID_원본 파일명
		String savedName = uuid.toString() + "_" + originalName;
		
		String savedPath = calcPath(uploadPath);
		
		// 배포된 서버 업로드 경로 + /2024/05/29/ + UUID_원본 파일 명으로 File target을 하나 만들어 놓는다.
		File target = new File(uploadPath + savedPath, savedName);
		FileCopyUtils.copy(fileData, target);		// 파일 복사
		
		// 확장자 추출
		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
		
		String uploadedFileName = savedPath.replace(File.separatorChar, '/') + "/" + savedName;
		
		if(MediaUtils.getMediaType(formatName) != null) {
			makeThumnail(uploadPath, savedPath, savedName);
		}
		
		// /2024/05/29/UUID_원본파일명
		return uploadedFileName;
	}

	private static void makeThumnail(String uploadPath, String savedPath, String savedName) throws Exception {
		// 썸네일 이미지를 만들기 위해 원본 이미지를 읽는다.
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + savedPath, savedName));
		
		// 썸네일 이미지를 만들기 위한 설정을 진행
		// Method.AUTOMATIC : 최소 시간 내에 가장 잘 보이는 이미지를 얻기 위한 사용 방식
		// Mode.FIT_TO_HEIGHT : 이미지 방향과 상관 없이 주어진 높이 내에서 가장 잘 맞는 이미지로 계산
		// targetSize : 값 100, 정사각형 사이즈로 100 x 100
		BufferedImage destImg =
				Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
		// 업로드한 원본 이미지를 가지고 's_'를 붙여서 임시 파일로 만들기 위해 썸네일 경로 + 파일명을 작성한다.
		String thumbnailName = uploadPath + savedPath + File.separator + "s_" + savedName;
		
		File newFile = new File(thumbnailName);
		String formatName = savedName.substring(savedName.lastIndexOf(".") + 1);
		
		// 's_'가 붙은 썸네일 이미지를 만든다.
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
	}

	private static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		String yearPath = File.separator + cal.get(Calendar.YEAR);		// 2024
		
		// DecimalFormat("00") : 두 자리에서 빈자리는 0으로 채움 
		String monthPath = yearPath + File.separator +
				new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);	// /2024/05
		String datePath = monthPath + File.separator +
				new DecimalFormat("00").format(cal.get(Calendar.DATE));	// /2024/05/29
		
		// 년 월 일 폴더 구조에 의한 폴더 생성
		makeDir(uploadPath, yearPath, monthPath, datePath);
		
		return datePath;
	}

	// 가변인자
	// 키워드 '...'를 사용한다
	// [사용법] 타입...변수명 형태로 사용
	// 순서대로 yearPath, monthPath, datePath가 배열로 들어가 처리
	private static void makeDir(String uploadPath, String...paths) {
		// /2024/05/29 폴더 구조가 존재한다면 return
		// 만들려던 폴더 구조가 이미 만들어져 있는 구조기 때문에 return을 하고
		// 그렇지 않으면 폴더 구조를 만들어준다.
		if(new File(paths[paths.length - 1]).exists()) {
			return;
		}
		for(String path : paths) {
			File dirPath = new File(uploadPath + path);
			
			// /2024/05/29 과 같은 경로에 각 폴더가 없으면 각각 만들어 진다.
			if(!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
	}
}
