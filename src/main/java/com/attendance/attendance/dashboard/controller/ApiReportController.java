@RestController
@RequestMapping("/api/reports")
public class ApiReportController {

    @Autowired private ReportService reportService;

    @GetMapping("/class/{classId}")
    public ResponseEntity<?> getClassReport(@PathVariable Long classId) {
        return ResponseEntity.ok(reportService.getClassAttendanceReport(classId));
    }
}
