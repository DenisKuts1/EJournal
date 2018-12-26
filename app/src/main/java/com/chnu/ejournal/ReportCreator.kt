package com.chnu.ejournal

import android.content.Context
import android.os.Environment
import android.widget.Toast
import com.chnu.ejournal.entities.Subject
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*


object ReportCreator {
    fun createReport(subject: Subject, context: Context) {
        val workbook = XSSFWorkbook()
        val sheet = workbook.createSheet("Report")
        val labsNameRow = sheet.createRow(0)
        subject.labs.forEachIndexed { index, lab ->
            val cell = labsNameRow.createCell(index + 1)
            cell.setCellValue(lab.name)
        }

        subject.group.students.forEachIndexed { index, student ->
            val row = sheet.createRow(index + 1)
            val studentCell = row.createCell(0)
            studentCell.setCellValue(student.name)
            subject.labs.forEachIndexed { i, lab ->
                val cell = row.createCell(i + 1)
                val point = lab.points[student]
                if(point != null){
                    cell.setCellValue(point.toString())
                } else {
                    cell.setCellValue("0")
                }
            }
        }
        println(Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED)
        val now = Date()
        val dateFormat = SimpleDateFormat("yyyyy-mm-dd-hh-mm-ss")
        val file = File(Environment.getExternalStorageDirectory(), "/report${dateFormat.format(now)}.xlsx")
        if(!file.exists()){
            file.mkdirs()
            file.createNewFile()
        }
        println(file.absolutePath)
        val stream = FileOutputStream(file)
        workbook.write(stream)
        stream.close()
        Toast.makeText(context, "URA", Toast.LENGTH_SHORT).show()


    }
}