package com.ezen.massagemall.admin.product;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.massagemall.admin.category.AdCategoryService;
import com.ezen.massagemall.admin.utils.FileUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RequestMapping("/admin/product/*")
@Slf4j
@Controller
public class AdProductController {

	private final AdProductService adProductService;
	private final AdCategoryService adCategoryService;
	private final FileUtils fileUtils;

	@Value("${com.ezen.upload.path}")
	private String uploadPath;

	@Value("${com.ezen.upload.ckeditor.path}")
	private String uploadCKPath;

	// 상품등록 폼
	@GetMapping("/pro_insert")
	public void pro_insert(Model model) {

		// List<CategoryVO> getFirstCategoryList =
		// adCategoryService.getFirstCategoryList();
		model.addAttribute("cate_list", adCategoryService.getFirstCategoryList());
	}

	// 상품등록 input type="file" id="pro_img_upload" name="pro_img_upload"
	@PostMapping("/pro_insert")
	public String pro_insert(ProductVO vo, MultipartFile pro_img_upload) throws Exception {
		// 1)상품이미지 파일 업로드 작업, 날짜폴더명 사용
		String dateFolder = fileUtils.getDateFolder();
		String saveFileName = fileUtils.uploadFile(uploadPath, dateFolder, pro_img_upload);

		vo.setPro_upfolder(dateFolder);
		vo.setPro_img(saveFileName);

		adProductService.pro_insert(vo);
		// 2) 상품 정보 DB저장
		return "redirect:/admin/product/pro_list";
	}

	@PostMapping("/imageupload")
	public void imageupload(HttpServletRequest request, HttpServletResponse response, MultipartFile upload)
			throws Exception {

		// 데이트 바이트단위로 작업하는 출력 스트림 최상위 클래스(추상클래스)
		OutputStream out = null;
		PrintWriter printWriter = null; // 서버에서 클라이언트에게 응답정보를 보낼때 사용(업로드 파일정보)

		// 예외처리 문법
		try {
			// 1) CKeditor 를 이용한 파일업로드 작업.
			String fileName = upload.getOriginalFilename();
			byte[] bytes = upload.getBytes();
			String ckUploadPath = uploadCKPath + "\\" + fileName;
			// out 객체생성하면 해당경로에 파일은 생성된다. 파일크기는 0바이트가 된다
			out = new FileOutputStream(ckUploadPath);
			out.write(bytes); // out 스트림객체에 파일 byte배열을 채웠다.
			out.flush(); // out스트림객체에 존재하고 있는 byte배열을 빈파일에 쓰는 작업

			// 2)업로드한 파일정보를 클라이언트인 CKEditor로 보내는 작업.
			printWriter = response.getWriter();

			String fileUrl = "/admin/product/display/" + fileName;
			// ckeditor 4.12에서 파일정보를 아래와 같이 작업을 하도록 가이드
			// Json 구조
			printWriter.println("{\"filename\" :\"" + fileName + "\", \"uploaded\":1,\"url\":\"" + fileUrl + "\"}"); // 스트림에
																														// 채움.
			printWriter.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (out != null) {

				try {

					out.close();// 객체소멸은 객체생성의 역순으로 close작업을 해준다
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		if (printWriter != null)
			printWriter.close();
	}

	// 이미지 파일을 CKEditor를 통하여 화면에 출력하기
	@GetMapping("/display/{fileName}")
	public ResponseEntity<byte[]> getFile(@PathVariable("fileName") String fileName) {
		ResponseEntity<byte[]> entity = null;

		try {
			entity = fileUtils.getFile(uploadCKPath, fileName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return entity;
	}

	@GetMapping("/pro_list")
	public void pro_list(Model model) throws Exception {

		List<ProductVO> pro_list = adProductService.pro_list();
		model.addAttribute("pro_list", pro_list);
	}

}