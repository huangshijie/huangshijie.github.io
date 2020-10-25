function ReadFiles()
{
   var fso, f1, ts, s;
   var ForReading = 1;
   fso = new ActiveXObject("Scripting.FileSystemObject");
   f1 = fso.CreateTextFile("c:\\testfile.txt", true);

   f1.WriteLine("Hello World");
   f1.WriteBlankLines(1);
   f1.Close();
   // 读取文件的内容。
  // Response.Write("Reading file <br>");
   ts = fso.OpenTextFile("c:\\testfile.txt", ForReading);
   s = ts.ReadLine();
  // Response.Write("File contents = '" + s + "'");
  alert(s);
   ts.Close();
}