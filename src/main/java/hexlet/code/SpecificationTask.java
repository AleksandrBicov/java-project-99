package hexlet.code;

import hexlet.code.dto.task.ParamsDTO;
import hexlet.code.model.Task;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class SpecificationTask implements Specification<Task> {

    @Override
    public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return null;
    }

    public Specification<Task> build(ParamsDTO params) {
        return withAssigneeId(params.getAssigneeId())
                .and(withTitleCont(params.getTitleCont()))
                .and(withStatus(params.getStatus()))
                .and(withLabelId(params.getLabelId()));
    }

    private Specification<Task> withTitleCont(String titleCont) {
        return (root, query, cb) -> titleCont == null
                ? cb.conjunction()
                : cb.like(root.get("name"), "%" + titleCont + "%");
    }

    private Specification<Task> withAssigneeId(Long assigneeId) {
        return (root, query, cb) -> assigneeId == null
                ? cb.conjunction()
                : cb.equal(root.get("assignee").get("id"), assigneeId);
    }

    private Specification<Task> withLabelId(Long labelId) {
        return (root, query, cb) -> labelId == null
                ? cb.conjunction()
                : cb.isMember(labelId, root.get("labels"));
    }

    private Specification<Task> withStatus(String status) {
        return (root, query, cb) -> status == null
                ? cb.conjunction()
                : cb.equal(root.get("taskStatus").get("slug"), status);
    }

}